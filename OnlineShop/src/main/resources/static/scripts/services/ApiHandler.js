app.service("ApiHandler", function ($http, $cookies) {

    this.callPost = (url, data, onSuccess, onError,setToken) => {

        url = "/api/" + url;
          let request = {
              url: url,
              method: 'POST',
              data: data
          }
        this.checkAndSetToken(request,setToken);

        $http(request).then((response)=> {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);
                } else if (result.haseError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: result.message,
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: "unKnow Error in Data",
                    })
                }
            }
        }, (err) => {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: "Error On server",
            })
            onError(err);
        });
    }

    this.callGet = (url,onSuccess, onError,setToken) =>{
        url = "/api/" + url;
        let request = {
            url:url,
            method:'GET'
        };
        this.checkAndSetToken(request,setToken);
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);
                } else if (result.haseError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: result.message,
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: "unKnow Error in Data",
                    })
                }
            }
        }, (err) => {
            if(err.status == 417)
            {
                $cookies.remove("userToken");
                location.href = "/login";
            }
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: "Error On server",
            })
            onError(err);
        });
    }

    this.callPut = (url, data, onSuccess, onError,setToken) => {
        url = "/api/" + url;
        let request = {
            url:url,
            method:'PUT',
            data:data
        }
        this.checkAndSetToken(request,setToken);
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);
                } else if (result.haseError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: result.message,
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: "unKnow Error in Data",
                    })
                }
            }
        }, (err) => {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: "Error On server",
            })
            onError(err);
        });
    }

    this.callDelete = (url,onSuccess, onError,setToken) =>{
        url = "/api/" + url;
        let request = {
            url:url,
            method:'DELETE'
        }
        this.checkAndSetToken(request,setToken);
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);
                }else if (result.haseError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: result.message,
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: "unKnow Error in Data",
                    })
                }
            }
        }, (err) => {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: "Error On server",
            })
            onError(err);
        });
    }

    this.checkAndSetToken = (request,setToken) =>{
        if(setToken){
            let token = $cookies.get("userToken");
            request.headers = {
                'Authorization':'Bearer '+token
            }
        }
    }


})