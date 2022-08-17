<!DOCTYPE html>


<html lang="en-US">
<head>
    <link href="libs/SliderShow/jquery.easy_slides.css" rel="stylesheet" type="text/css"/>
    <script src="libs/SliderShow/easy_slides.js"></script>

    <title>Onlin Shop App | Home</title>
    <jsp:include page="Headers/HomeTitle.jsp"></jsp:include>
</head>
<body ng-app="onlineShopApp" ng-controller="homeCtrl">


<jsp:include page="partial/homeNav.jsp"></jsp:include>
<div class="row">
    <img src="images/shopPeople.jpg" width="100%">
</div>

<div ng-controller="sliderCtrl">

    <div  class="slider slider_circle_10">
        <div ng-repeat="data in sliderList">
            <a href="{{data.link}}">
                <img ng-src="/api/utils/upload/files/{{data.image}}" ng-if="data.image">
            </a>
        </div>
        <div class="next_button"></div>
        <div class="prev_button"></div>
    </div>
</div>

<div ng-controller="productCtrl" class="m-1">
    <h1 class="text-center">NEW Products</h1>
    <br/>
    <div class="container mb-3">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col" ng-repeat="data in newProductList">
                <div class="card h-100">
                    <a href="oneProduct/{{data.id}}">
                        <img ng-src="/api/utils/upload/files/{{data.image}}" class="card-img-top imageCardSize"
                             alt="{{data.title}}">
                    </a>
                    <div class="card-body">
                        <h5 class="card-title">{{data.title}}</h5>
                        <p class="card-text"><b>Detail of production :</b><span
                                ng-bind-html="data.description.substring(0,100)"></span>.....</p>
                        <p class="card-text" ng-repeat="f in data.featuresDataList">
                            {{f.key}} : {{f.value}}
                        </p>
                        <p class="card-text"><b>price :</b>{{data.price}}</p>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted">{{getTime(data.lastdate)}}</small>
                        <a class="btn btn-sm btn-primary float-sm-end" href="oneProduct/{{data.id}}">Add to Basket</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="parallex-cover"></div>
    <h1 class="text-center">Popular Products</h1>
    <br/>
    <div class="container mb-3">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col" ng-repeat="data in popularProduction">
                <div class="card h-100">
                    <a href="oneProduct/{{data.id}}">
                        <img ng-src="/api/utils/upload/files/{{data.image}}" class="card-img-top imageCardSize"
                             alt="{{data.title}}">
                    </a>
                    <div class="card-body">
                        <h5 class="card-title">{{data.title}}</h5>
                        <p class="card-text"><b>Detail of production :</b> <span
                                ng-bind-html="data.description.substr(0,100)"></span>.....</p>
                        <p class="card-text" ng-repeat="f in data.featuresDataList">
                            {{f.key}} : {{f.value}}
                        </p>
                        <p class="card-text"><b>price :</b> {{data.price}} </p>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted">{{getTime(data.lastdate)}}</small>
                        <a class="btn btn-sm btn-primary float-sm-end" href="oneProduct/{{data.id}}">Add to Basket</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<br/>

<jsp:include page="partial/homeFooter.jsp"></jsp:include>

</body>

</html>