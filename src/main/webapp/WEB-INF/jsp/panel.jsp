<%@ page import="holosen.shop.app.helper.uiModels.people.UserVM" %>
<%@ page import="holosen.shop.app.enums.UserRole" %>
<%@ page import="holosen.shop.app.helper.uiModels.people.CustomerVM" %>
<!DOCTYPE html>


<html lang="en-US">
<head>
    <title>Onlin Shop App | Panel</title>
    <jsp:include page="Headers/panelHeader.jsp"></jsp:include>

    <%
        UserVM uservm = (UserVM) request.getAttribute("user");
        CustomerVM customerVM = (CustomerVM) request.getAttribute("customer");
        long i = -1;
        if(customerVM != null){
            i = customerVM.getId();
        }
    %>
</head>

<body ng-app="onlineShopApp">
<div class="container-fluid" ng-controller="panelCtrl" ng-init="init(<%= i %>)">
    <div class="row">
        <div class="panel-header ">
            <a ng-click="Logout()" class="btn btn-danger btn-sm">Logout</a>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-2 p-0">

            <div class="side-nave">
                <div class="p-2 text-center">
                    <img src="images/useravatar.webp" width="100"><br>
                    <span>{{user.fullName}}</span>
                </div>

                <ul>
                    <li>
                        <a href="/" >
                            <i class="fa fa-home"></i>
                            <span>Website</span>
                        </a>
                    </li>
                    <% if(uservm.getRole() == UserRole.Admin || uservm.getRole() == UserRole.User) { %>
                    <li ng-click="changeMenu('dashboard')" ng-class="{'nav-side-active':templateGtoup == 'dashboard'}">
                        <a href="#" >
                            <i class="fa fa-link"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('nav-list')" ng-class="{'nav-side-active':templateGtoup == 'nav'}">
                        <a href="#" >
                            <i class="fa fa-link"></i>
                            <span>Navigations</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('content-list')" ng-class="{'nav-side-active':templateGtoup == 'content'}">
                        <a href="#">
                            <i class="fa fa-file-alt"></i>
                            <span>Content</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('slider-list')" ng-class="{'nav-side-active':templateGtoup == 'slider'}">
                        <a href="#">
                            <i class="fa fa-photo-film"></i>
                            <span>Slider</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('blog-list')" ng-class="{'nav-side-active':templateGtoup == 'blog'}">
                        <a href="#">
                            <i class="fa fa-newspaper"></i>
                            <span>Blog</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('category-list')" ng-class="{'nav-side-active':templateGtoup == 'product'}">
                        <a href="#">
                            <i class="fa fa-cubes"></i>
                            <span>Products</span>
                        </a>
                    </li>
                    <li ng-hide=" user.role == 'User'" ng-click="changeMenu('user-list')" ng-class="{'nav-side-active':templateGtoup == 'user'}">
                        <a href="#">
                            <i class="fa fa-users"></i>
                            <span>User</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('List-Customer')" ng-class="{'nav-side-active':templateGtoup == 'List-Customer'}">
                        <a href="#">
                            <i class="fa fa-cubes"></i>
                            <span>Customer</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('uploader')" ng-class="{'nav-side-active':templateGtoup == 'uploader'}">
                        <a href="#">
                            <i class="fa fa-photo-film"></i>
                            <span>File Manager</span>
                        </a>
                    </li>
                    <% } else { %>
                    <li ng-click="changeMenu('CustomerDashboard')" ng-class="{'nav-side-active':templateGtoup == 'CustomerDashboard'}">
                        <a href="#">
                            <i class="fa fa-dashboard"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li ng-click="changeMenu('CustomerProfile')" ng-class="{'nav-side-active':templateGtoup == 'CustomerProfile'}">
                        <a href="#">
                            <i class="fa fa-user"></i>
                            <span>Profile</span>
                        </a>
                    </li>

                    <% }%>
                </ul>
            </div>
        </div>
        <div class="col-sm-10 main-container p-0" ng-include="template"> </div>
    </div>

</div>
</body>
</html>
