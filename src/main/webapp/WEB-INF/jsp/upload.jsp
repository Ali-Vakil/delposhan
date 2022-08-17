<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Spring Boot File Upload with AngularJS</title>
    <meta charset="utf-8" />
    <!-- Check other AngularJS version at: -->
    <!-- https://code.angularjs.org/1.6.9/docs/misc/downloading -->
    <script src="/libs/angular.min.js"></script>
    <script src="/scripts/directives/fileModel.js"></script>
    <script src="/scripts/controllers/utils/UploadFileController.js"></script>
    <script src="/scripts/controllers/utils/GetFilesCtrl.js"></script>
</head>
<body ng-app="MainApp">
<h2>Spring Boot File Upload with AngularJS</h2>
<div ng-controller="UploadFileController">
    <form>
        Description: <br/>
        <input type="text" name="description" ng-model="myForm.description" style="width:350px;"/>
        <br/><br/>
        File to upload : <input type="file" file-model="myForm.files"/><br />

        <button type="button" ng-click="doUploadFile()">Upload</button>
    </form>
    <h2>Upload Results:</h2>
    <div style="border:1px solid #ccc;padding: 5px;">
        <span ng-bind="uploadResult"></span>
    </div>
</div>
<!-- Get Files -->
<hr>
<div ng-controller="GetFilesController">
    <button type="button" ng-click="getAllFiles()">Get All Files</button>
    <ul>
        <li ng-repeat="file in allFiles">
            <a href='/utils/upload/files/{{file}}'>{{file}}</a>
        </li>
    </ul>
</div>
</body>
</html>