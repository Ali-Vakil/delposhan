app.controller('profileCtrl', function ($scope, ApiHandler, $rootScope) {

    $scope.data = {};
    $scope.customerId;
    $scope.chpass = false;
    $scope.chuser = false;
    $scope.validation = {
        check: true,
        message: ""
    }

    $scope.getdatalist = () => {
        let url = 'Customer/' + $scope.customerId + "/";

        ApiHandler.callGet(url, (response) => {

            $scope.data = response.datalist[0];
        }, (err) => {

        }, true);
    }

    $scope.init = () => {
        $scope.customerId = $rootScope.currentCustomerId;
        $scope.getdatalist();
    }
    $scope.init();


    $scope.changeUser = () => {
        if ($scope.chuser) {
            document.getElementById("username").readOnly = true;
            $scope.chuser = false;
        } else {
            document.getElementById("username").readOnly = false;
            $scope.chuser = true;
        }
    }
    $scope.changePass = () => {
        if ($scope.chpass)
            $scope.chpass = false;
        else
            $scope.chpass = true;
    }
    $scope.Update = () => {
        let validation = $scope.customerDataValidation();


        if (!$scope.validation.check) {
            swal.fire($scope.validation.message);
            return;
        }


        if ($scope.chpass) {
            let pass = document.getElementById("oldPass").value;
            let newpass = document.getElementById("newPass").value;

            let userVM = {
                id: $scope.data.user.id,
                password: pass,
                newPassword: newpass
            }


            ApiHandler.callPut("user/chengPassword", userVM, (response) => {
               swal.fire("Your Password is Updated successFully");
            }, (err) => {
            }, true)


        }

        if ($scope.chuser) {
            ApiHandler.callPut("Customer/withUser", $scope.data, (responce) => {
                location.href = "login"
            }, (error) => {
                Swal.fire(error.toString());
            }, true)
        } else {
            ApiHandler.callPut("Customer/", $scope.data, (responce) => {
                }, (error) => {
                Swal.fire(error.toString());
            }, true)
        }
        swal.fire("Your Profile is Updated successFully");
        $scope.changeMenu("CustomerDashboard")
    }

    $scope.customerDataValidation = () => {
        $scope.validation.check = true;
        $scope.validation.message = "";

        var check = true;
        var Message = "";
        if ($scope.data.firstName == null || $scope.data.firstName === "" || $scope.data.firstName === undefined) {
            Message += "Your firstname is Empty \n";
            document.getElementById("firstName").style.borderColor = "red";
            check = false;
        }
        if ($scope.data.lastName === null || $scope.data.lastName === undefined || $scope.data.lastName === "") {
            Message += "Your lastName is Empty \n";
            document.getElementById("lastName").style.borderColor = "red";
            check = false;
        }
        if ($scope.data.mobile === undefined || $scope.data.mobile == null || $scope.data.mobile === "") {
            Message += "Your mobile is Empty \n";
            document.getElementById("mobile").style.borderColor = "red";
            check = false;
        } else if ($scope.data.mobile.toString().length < 11 ||
            isNaN($scope.data.mobile) ||
            (!$scope.data.mobile.toString().startsWith("09") && !$scope.data.mobile.toString().startsWith("98"))) {
            Message += "Your mobile Number is not Correct \n";
            document.getElementById("mobile").style.borderColor = "red";
            check = false;
        }
        if ($scope.data.email === undefined || $scope.data.email.toString() == null || $scope.data.email.toString() == "") {
            Message += "The email address is Empty \n";
            document.getElementById("Email").style.borderColor = "red";
            check = false;
        } else if ($scope.data.email != null) {
            let regex_pattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if (!regex_pattern.test($scope.data.email)) {
                Message += 'The email address is not valid \n';
                document.getElementById("Email").style.borderColor = "red";
                check = false;
            }
        }
        if ($scope.data.address === undefined || $scope.data.address === null || $scope.data.address === "") {
            Message += "Your address is Empty \n";
            document.getElementById("address").style.borderColor = "red";
            check = false;
        }
        if ($scope.data.postalCode === undefined ||
            $scope.data.postalCode.toString() == null ||
            $scope.data.postalCode.toString().trim() === "" ||
            $scope.data.postalCode.toString().length !== 10 ||
            isNaN($scope.data.postalCode)) {
            Message += "Your postalCode : is Empty \n OR: not number \n OR: not equals Exactly 10 number";
            document.getElementById("postalCod").style.borderColor = "red";
            check = false;

        }

        if ($scope.data.user.username === undefined ||
            $scope.data.user.username === null ||
            $scope.data.user.username === "") {
            Message += "\n Your username is Empty ";
            document.getElementById("username").style.borderColor = "red";
            check = false;
        }
        if ($scope.chpass) {
            let pass = document.getElementById("oldPass").value;
            let newpass = document.getElementById("newPass").value;
            let confirmpass = document.getElementById("conformPass").value;

            if (pass === undefined || pass === null || pass === "") {
                Message += "\n Your password is Empty";
                document.getElementById("oldPass").style.borderColor = "red";
                check = false;
            }
            if (newpass === undefined || newpass === null || newpass === "") {
                Message += "\n Your new password is Empty";
                document.getElementById("newPass").style.borderColor = "red";
                check = false;
            }
            if (confirmpass === undefined || confirmpass === null || confirmpass === "") {
                Message += "\n Your conform password is Empty";
                document.getElementById("conformPass").style.borderColor = "red";
                check = false;
            }
            if (newpass != confirmpass) {
                Message += "\n Your new password and it's confirm in not match";
                document.getElementById("newPass").style.borderColor = "red";
                document.getElementById("conformPass").style.borderColor = "red";
                check = false;
            }

        }

        $scope.validation.check = check;
        $scope.validation.message = Message;
        return $scope.validation;

    }
});