var app = angular.module("onlineShopApp", ['ngCookies','ngSanitize']);


app.filter('removeHTMLTags', function () {
    return function (text) {
        if(text != undefined) {
            text = text.replace(/<[^>]+>/gm, '');
        }
        return text;
    };

});