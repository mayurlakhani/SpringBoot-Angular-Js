'use strict';
App.factory('RoleService',['$http',function($http) {
							//************fetch record data:****************
	return {
		fetchAllroles : function() {
			return $http.get('http://localhost:8084/role/all').success(function(data) {
				return data;
			}).error(function(data, status, headers, config) {
				return data;
			});
		},
	}

}])

//******USER*****//
//save user:
App.factory('UserService',['$http',function($http) {								
		return {	
		createUser : function(formData) {
			var httpParam = {};
			httpParam.method = 'POST';
			httpParam.url = 'http://localhost:8084/datumuser/save';
			httpParam.data = formData;
			httpParam.headers = {
				'Content-Type' : undefined
			};
			httpParam.transformRequest = angular.identity;
			return $http(httpParam).success(function(data) {
				$scope.message = data.message;
				return data;
			}).error(function(data, status, headers, config) {
				return status																																																																																																																														;
			});

		},
		
		//fetch all data:
		fetchAllUsers : function() {
			return $http.get('http://localhost:8084/datumuser/all').success(function(data) {
				return data;
			}).error(function(data, status, headers, config) {
				return status;
			});
		},
		
		//fetch by order of email:
		fetchOrderByEmailAsc : function() {
			return $http.get('http://localhost:8084/datumuser/orderByEmail').success(function(data) {
				return data;
			}).error(function(data, status, headers, config) {
				return data;
			});
		},
		
		//search by Keyword:
		searchUser : function(keyword) {
			return $http.get('http://localhost:8084/datumuser/search?keyword='+keyword).success(function(data) {
				return data;
			}).error(function(data, status, headers, config) {
				return data;
			});
		},
		
		//delete user:
		deleteUser : function(id) {
			var httpParam = {};
			httpParam.method = 'GET';
			httpParam.url = 'http://localhost:8084/datumuser/delete/'+ id;
			httpParam.headers = {
				'Content-Type' : undefined
			};
			httpParam.transformRequest = angular.identity;
			return $http(httpParam).success(function(data) {
				return data;
			}).error(function(data, status, headers, config) {
				return data;
			});	
		},
				
		// Get User by id:
		getUser : function(id) {
			return $http.get('http://localhost:8084/datumuser/get/'+id).success(function(data) {
				return data;
			}).error(function(data, status, headers, config) {
				return data;
			});
		}
		
		}
	}
		

]);
App.service('Paginator', function ($rootScope) {
    this.page = 0;
    this.rowsPerPage = 50;
    this.itemCount = 0;
    this.limitPerPage = 5;

    this.setPage = function (page) {
        if (page > this.pageCount()) {
            return;
        }

        this.page = page;
    };

    this.nextPage = function () {
        if (this.isLastPage()) {
            return;
        }

        this.page++;
    };

    this.perviousPage = function () {
        if (this.isFirstPage()) {
            return;
        }

        this.page--;
    };

    this.firstPage = function () {
        this.page = 0;
    };

    this.lastPage = function () {
        this.page = this.pageCount() - 1;
    };

    this.isFirstPage = function () {
        return this.page == 0;
    };

    this.isLastPage = function () {
        return this.page == this.pageCount() - 1;
    };

    this.pageCount = function () {
        return Math.ceil(parseInt(this.itemCount) / parseInt(this.rowsPerPage));
    };
    
    this.lowerLimit = function() { 
        var pageCountLimitPerPageDiff = this.pageCount() - this.limitPerPage;
        
        if (pageCountLimitPerPageDiff < 0) { 
            return 0; 
        }
        
        if (this.page > pageCountLimitPerPageDiff + 1) { 
            return pageCountLimitPerPageDiff; 
        } 
        
        var low = this.page - (Math.ceil(this.limitPerPage/2) - 1); 
        
        return Math.max(low, 0);
    };
});
App.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        })
        .error(function(){
        });
    }
}]);
