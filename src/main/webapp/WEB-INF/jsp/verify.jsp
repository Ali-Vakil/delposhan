<%@ page import="holosen.shop.app.entities.order.Transactions" %>
<!DOCTYPE html>


<html lang="en-US">
<head>
    <title>Onlin Shop App | Verify</title>
    <jsp:include page="Headers/HomeTitle.jsp"></jsp:include>

    <%
        Transactions transaction = (Transactions) request.getAttribute("transaction");
    %>
</head>

<body ng-app="onlineShopApp" >
<jsp:include page="partial/homeNav.jsp"></jsp:include>
<div  ng-controller="verifyCtrl" class="min-heiht-500">
    <div class="col col-1"></div>
    <div class="col container-fluid mt-3 mb-3">
            <div class="card text-center card-side-Margin">
                <div class="card-header verifytitel">
                    -- Payment Verify --
                </div>
                <div class="card-body" ng-controller="verifyCtrl">
                    <p class="card-text verifyBody">
                        Amount : <%=transaction.getAmount()%>
                        <br/>
                        Status : <% if (transaction.getVerifyStatus().equals("OK")) {%>
                        <span class="badge bg-success"> SuccessFully</span>
                        <br/>
                        ReferenceId : <%=transaction.getRefId()%>
                        <%} else {%>
                        <span class="badge bg-danger"> Failed</span>
                        <%}%>
                    </p>
            </div>
        </div>
    </div>
    <div class="col-1"></div>
</div>
<jsp:include page="partial/homeFooter.jsp"></jsp:include>
</body>
</html>