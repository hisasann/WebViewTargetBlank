# iOS、AndroidのWebViewでtarget="_blank"なリンクを踏んだ場合の挙動調査

> iOSは6、Androidは4.xで調査しました

##target="_blank"なリンクを踏んだ先での画面での挙動について

### iOS - history.back

* なぜか戻れる

### iOS - window.close

* 閉じれない

### Android - history.back

* なぜか戻れる

### Android - window.close

* 閉じれない
* targetblankで開かれたときにwindow.closeを実行してしまうと、その後にhistory.backが効かなくなる

結論としては、アプリから使われるであろうWebView用のサイトはtarget="_blank"は使わずに、
それ相当の文字列を付与してアプリ側に通知などするほうがよさそう

たとえば"app::targetblank::http://hogehoge.com/"のようなURLにしておき、
shouldStartLoadWithRequestメソッド内でパースしてネイティブブラウザを開くなど

    if ([components count] > 1 &&
            [(NSString *) [components objectAtIndex:0] isEqualToString:@"app"]) {
        if ([(NSString *) [components objectAtIndex:1] isEqualToString:@"targetBlank"]) {
            // ネイティブのブラウザで開いて欲しいURLはここでキャッチする
            // example - mynavi:targetBlank::http://hisasann.com/
            if (navigationType == UIWebViewNavigationTypeLinkClicked) {
                NSURL *targetBlankUrl = [NSURL URLWithString:[components objectAtIndex:2]];
                [[UIApplication sharedApplication] openURL:targetBlankUrl];
                return NO;
            }
        }

        return NO;
    }

