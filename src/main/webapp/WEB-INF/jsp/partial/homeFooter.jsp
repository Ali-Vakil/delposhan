<footer class="main-footer" ng-controller="contentCtrl">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-5">
                <div class="row">
                    <div class="col-md-2"> <i class="fa fa-map-pin footer-circle-item" style="width:50px;height: 50px;"></i></div>
                    <div class="col-md-10 footer-circle-item-detail" ng-bind-html="getContent('Address')"></di>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <i class="fa fa-phone-alt footer-circle-item"></i>
                    </div>
                    <a class="col-md-10 footer-circle-item-detail" href="tel:{{getContent('Tel') | removeHTMLTags}}">{{getContent('Tel') | removeHTMLTags}}</a>
                </div>
                <div class="row ">
                    <div class="col-md-2">
                        <i class="fa fa-mail-bulk footer-circle-item"></i>
                    </div>
                    <a class="col-md-10 footer-circle-item-detail" href="mailto:{{getContent('Email') | removeHTMLTags}}">{{getContent('Email') | removeHTMLTags}}</a>
                </div>

            </div>
            </div>
            <div class="col-md-5" >
                <h5>About The Company</h5>
                <br/>
                 <P  ng-bind-html="getContent('About').substr(0,300)"></P>
                <br/>
                <div class="footer-social">
                    <a href="{{getContent('facebook-link') | removeHTMLTags }}">
                        <i class="fa fa-link"></i>
                    </a>
                </div>
                <div class="footer-social">
                    <a href="{{getContent('twitter-link') | removeHTMLTags }}">
                        <i class="fa fa-link"></i>
                    </a>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
</footer>