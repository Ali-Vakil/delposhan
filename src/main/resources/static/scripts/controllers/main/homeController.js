app.controller("homeCtrl",function ($scope,mainApiHandler,$rootScope){

    $rootScope.page_ = "Home";

    function func() {
        EasySlides('.slider_circle_10', {
            'autoplay': true,
            'show': 13
        });
    }

    setTimeout(func, 300);
});