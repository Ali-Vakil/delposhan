app.controller("paymentCtrl", function ($scope, $http, mainApiHandler, $rootScope, $cookies) {

    $rootScope.page_ = "payment";
    // $scope.getaboutData();

    $scope.dataList = [];
    $scope.totoalCount = 0;
    $scope.totalPrice = 0;
    $scope.User = {};
    $scope.Customer = {};
    $scope.data = {}; //data is Customer data
    $scope.PymentType = "zarinpal"
    $scope.userLogin = false;
    $scope.validation ={
        check:true,
        message: ""
    }

    $scope.filldataList = () => {
        if ($cookies.get("basket") == undefined ||
            $cookies.get("basket") == null) {
            $scope.dataList = [];
            return;
        }
        $scope.dataList = JSON.parse($cookies.get("basket"));
        for (let i = 0; i < $scope.dataList.length; i++) {
            $scope.totoalCount += parseInt($scope.dataList[i].count);
            $scope.totalPrice += $scope.dataList[i].price * $scope.dataList[i].count;
        }
    };

    $scope.gotoPayment = () => {
        let OrderItem_ = [];
        for (let i = 0; i < $scope.dataList.length; i++) {
            let item = $scope.dataList[i];
            let totalPrice = $scope.dataList[i].count * $scope.dataList[i].price;
            OrderItem_.push({
                productId: item.productId,
                count: item.count,
                colorId: item.color,
                sizeId: item.size,
                price: totalPrice
            })
        }

        if (OrderItem_.length == 0) {
            Swal.fire("Your Basket is empty");
            return;
        }

        if ($scope.paymentType == undefined || $scope.paymentType == null) {
            $scope.paymentType = "zarinpal"
        }

        let validation = $scope.customerDataValidation();

        if(!validation.check){
            swal.fire(validation.message);
            return;
        }


        let paymentVM = {
            customerVM: $scope.data,
            orderItemVMList: OrderItem_,
            paymentType: $scope.paymentType,
            userLogin :$scope.userLogin
        };



        mainApiHandler.callPost("Payment/", paymentVM, (response) => {
            let href = response.datalist[0].location;
            location.href = href;
        });
    }

    $scope.init = () => {
        var token = $cookies.get("userToken");
        if (token != null && token != undefined) {
            $scope.getUserInfo(token);
        }
    }


    $scope.getUserInfo = (token) => {

        let request = {
            url: '/api/user/getUserInfo',
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        };
        $http(request).then((response) => {
            if (response != null && response.data != null) {

                $scope.User = response.data.datalist[0]
                $scope.userLogin = true;
                if ($scope.User != null && $scope.User != undefined) {

                    $scope.data.username = $scope.User.userName;
                    $scope.data.email = $scope.User.email;
                    let id = 0;
                    id = $scope.User.id;
                    $scope.getCustomerInfo(id);

                }
            }
        });
    }
    $scope.getCustomerInfo = (UserId) => {

        mainApiHandler.callGet('Customer/getByUserId/' + UserId, (response) => {
            $scope.Customer = response.datalist[0];
            $scope.data.firstName = $scope.Customer.firstName;
            $scope.data.lastName = $scope.Customer.lastName;
            $scope.data.mobile = $scope.Customer.mobile;
            $scope.data.tel = $scope.Customer.tel;
            $scope.data.address = $scope.Customer.address;
            $scope.data.postalCode = parseInt($scope.Customer.postalCode);
        });
    }

    $scope.filldataList();
    $scope.init();

    $scope.customerDataValidation =()=>{
        $scope.validation.check = true;
        $scope.validation.message = "";

        var check = true;
        var Message = "";
        if ($scope.data.firstName == null || $scope.data.firstName == "" || $scope.data.firstName == undefined) {
            Message += "Your firstname is Empty \n";
            document.getElementById("firstName").style.borderColor = "red";
            check = false;
        }
        if ($scope.data.lastName == null || $scope.data.lastName == undefined || $scope.data.lastName == "") {
            Message += "Your lastName is Empty \n";
            document.getElementById("lastName").style.borderColor = "red";
            check = false;
        }
        if ($scope.data.mobile == undefined || $scope.data.mobile == null || $scope.data.mobile == "") {
            Message += "Your mobile is Empty \n";
            document.getElementById("mobile").style.borderColor = "red";
            check = false;
        }else if ($scope.data.mobile.toString().length < 11 ||
            isNaN($scope.data.mobile) ||
            (!$scope.data.mobile.toString().startsWith("09") && !$scope.data.mobile.toString().startsWith("98"))) {
            Message += "Your mobile Number is not Correct \n";
            document.getElementById("mobile").style.borderColor = "red";
            check = false;
        }
        if ( $scope.data.email == undefined || $scope.data.email.toString() == null || $scope.data.email.toString() == "") {
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
        if ($scope.data.address == undefined || $scope.data.address == null || $scope.data.address == "") {
            Message += "Your address is Empty \n";
            document.getElementById("address").style.borderColor = "red";
            check = false;
        }
        if ($scope.data.postalCode == undefined ||
            $scope.data.postalCode.toString() == null ||
            $scope.data.postalCode.toString().trim() == "" ||
            $scope.data.postalCode.toString().length != 10 ||
            isNaN($scope.data.postalCode)) {
            Message += "Your postalCode : is Empty \n OR: not number \n OR: not equals Exactly 10 number";
            document.getElementById("postalCod").style.borderColor = "red";
            check = false;

        }
        if(!$scope.userLogin){
            if ($scope.data.username == undefined ||
                $scope.data.username == null ||
                $scope.data.username == "") {
                Message += "\n Your username is Empty ";
                document.getElementById("username").style.borderColor = "red";
                check = false;
            }
            if ($scope.data.password == undefined ||
                $scope.data.password == null ||
                $scope.data.password == "") {
                Message += "\n Your password is Empty";
                document.getElementById("password").style.borderColor = "red";
                check = false;
            }
        }

        $scope.validation.check = check;
        $scope.validation.message = Message;
        return $scope.validation;

    }



});