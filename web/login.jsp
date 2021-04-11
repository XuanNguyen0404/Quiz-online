<%-- 
    Document   : login
    Created on : Feb 16, 2021, 1:04:13 PM
    Author     : tienx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link href="css/now-ui-kit.css" rel="stylesheet" />
        <title>Login Page</title>
    </head>
    <body class="login-page sidebar-collapse">
        <nav class="navbar navbar-expand-lg bg-primary fixed-top navbar-transparent " color-on-scroll="400">
            <div class="container">
                
                <div class="navbar-translate">
                    <a class="navbar-brand" href="index.jsp">
                        Quiz Online
                    </a>                   
                </div>
                <div class="collapse navbar-collapse justify-content-end" id="navigation" data-nav-image="img/login_img.jpg">
                    <ul class="navbar-nav">
                        
                    </ul>
                </div>
            </div>
        </nav>
        <!-- End Navbar -->
        <div class="page-header clear-filter" filter-color="orange">
            <div class="page-header-image" style="background-image:url(img/login_img.jpg)"></div>
            <div class="content">
                <div class="container">
                    <div class="col-md-4 ml-auto mr-auto">
                        <h1>Login</h1>
                        <div class="card card-login card-plain">
                            <form class="form" method="POST" action="LoginController">
                                
                                <div class="card-body">
                                    <div class="input-group no-border input-lg">
                                        
                                        <input type="email" class="form-control" placeholder="Email" required="true"
                                               name="txtEmail">
                                    </div>
                                    <div class="input-group no-border input-lg">
                                        
                                        <input type="password" placeholder="Password" class="form-control" required="true"
                                               name="txtPassword"/>
                                    </div>
                                    <label class="badge-danger small">${requestScope.INVALID}</label>
                                </div>
                                <div class="card-footer text-center">
                                    <button type="submit" class="btn btn-info btn-round btn-lg btn-block" 
                                            value="Login" name="action" >Get Started</button>
                                    <div class="pull-left">
                                        <h6>
                                            <a href="register.jsp" class="link">Create Account</a>
                                        </h6>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        
        <footer class="footer">
            <div class=" container ">
                <nav>
                    <ul>                       
                        <li>
                            <h5>About Us</h5>
                        </li>
                    </ul>
                </nav>
                <div class="copyright" id="copyright">
                    &copy;
                    Copy right by Xuan             
                </div>
            </div>
        </footer>
        </div>
              
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="js/now-ui-kit.js"></script>
</body>
</html>
