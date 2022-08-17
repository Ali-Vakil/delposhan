<!DOCTYPE html>


<html lang="en-US">
<head>
    <title>Angularjs demo application</title>
    <jsp:include page="Headers/loginHeader.jsp"></jsp:include>
</head>

<body ng-app="onlineShopApp" ng-controller="loginCtrl">
<div class="container-fluid">
    <div class="row">
        <div class="col-3"></div>
        <div class="col login-box-holder">
            <h3>Login to panel</h3>
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" ng-model="user.username">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" ng-model="user.password">
            </div>
            <button type="submit" class="btn btn-primary" ng-click="doLogin()">Login</button>
        </div>
        <div class="col-3"></div>
    </div>

</div>
</body>
</html>
