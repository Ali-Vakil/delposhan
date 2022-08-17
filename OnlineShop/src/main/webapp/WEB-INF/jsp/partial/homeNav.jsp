<nav class="navbar navbar-expand-lg navbar-light bg-light" ng-controller="navCtrl">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">
            <img src="/images/new-logo-white1.png"width="60">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item" ng-repeat="item in navList"  ng-class="{'Title-active' : page_ == item.title}">
                    <a class="nav-link" aria-current="page"  href="{{item.link}}">{{item.title}}</a>
                </li>
            </ul>
            <div class="d-flex">
                <a class="btn btn-outline-success m-r-10" href="/panel" ><i class="fa fa-user"></i></a>
                <a class="btn btn-outline-primary m-r-10" href="/basket" ><i class="fa fa-basket-shopping"></i></a>
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" ng-model="searchKey">
                <button class="btn btn-outline-success" type="submit" ng-click="search()">Search</button>
            </div>
        </div>
    </div>
</nav>
