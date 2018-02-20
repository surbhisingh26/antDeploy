//var casper = require('casper').create();


casper.test.begin('The heading exists', 2, function suite(test) {
    casper.start('http://localhost:8080/webProject1/home.jsp', function() {
    	test.assertExists('button.btn');
    	 test.assertSelectorHasText('p.text-center', 'We love fans');
    }).run(function() {
        test.done();
    });
});