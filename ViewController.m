#import "ViewController.h"

@interface ViewController ()

@property(weak, nonatomic) IBOutlet UIWebView *webview;
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];

    self.webview.delegate = self;

    NSURL *url = [NSURL URLWithString:@"http://hogehoge.com/"];
    NSURLRequest *urlRequest = [NSURLRequest requestWithURL:url];
    [self.webview loadRequest:urlRequest];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType {

    NSString *absoluteUrl = request.URL.absoluteString;

    NSLog(@"absoluteUrl - %@", absoluteUrl);

    if (navigationType == UIWebViewNavigationTypeLinkClicked) {
        NSLog(@"1");
    }

    return YES;
}

@end
