app.controller("loginCtrl", function ($scope, ApiHandler, $cookies) {
    $scope.user = {};
    $scope.doLogin = () => {

        if ($scope.user.username == null || $scope.user.username == "" || $scope.user.password == null || $scope.user.password == "") {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Please Fill Username And Password!',
            })
            return;
        }

        ApiHandler.callPost('user/login'
            , $scope.user
            , (response) => {
                let token = response.datalist[0].token;
                if (token == null || token == "") {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Invalid token!',
                    })

                    return;
                }
                $cookies.put('userToken', token);
                location.href = "/panel"
            }
            , (error) => {
            }
            , false
        )
    }
});