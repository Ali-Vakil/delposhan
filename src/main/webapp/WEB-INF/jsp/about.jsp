<!DOCTYPE html>


<html lang="en-US">
<head>
    <title>Onlin Shop App | About</title>
    <jsp:include page="Headers/HomeTitle.jsp"></jsp:include>
</head>

<body ng-app="onlineShopApp" ng-controller="aboutCtrl">
<jsp:include page="partial/homeNav.jsp"></jsp:include>
<div ng-controller="aboutCtrl">
    <img src="images/about.jpg" width="100%">
</div>
<div class="container-fluid mt-3 mb-3" >
    <div class="col">
        <div class="card text-center">
            <div class="card-header">
                -- ABOUT US --
            </div>
            <div class="card-body" ng-controller="contentCtrl">
                <h5 class="card-title">On line Shop</h5>
                <P ng-bind-html="getContent('About')"></P>
                <a href="/product" class="btn btn-primary">Lets go to shop</a>
            </div>
            <div class="card-footer text-muted">
                NEVER GIVE UP
            </div>
        </div>
    </div>
</div>
<jsp:include page="partial/homeFooter.jsp"></jsp:include>
</body>
</html>