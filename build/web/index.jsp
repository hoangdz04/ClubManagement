<%-- 
    Document   : index
    Created on : Feb 26, 2025, 9:47:59 PM
    Author     : A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Club Management</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="assets/css/style.css"/>
    </head>
    <body>
        <nav>
            <div class="nav_header">
                <div class="nav_logo">
                    <a href="#">Ctr<span>Club+</span></a>
                </div>
                <div class="nav_menu_btn" id="menu-btn">
                    <span><i class="bi bi-list"></i></span>
                </div>
            </div>
            <ul class="nav_links" id="nav-links">
                <li><a href="#">Events</a></li>
                <li><a href="#">Membership</a></li>
                <li><a href="#">Blog</a></li>
                <li><a href="#">About the Club</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
            <div class="nav_btns">
                <form action="view/login.jsp" method="post">
                <input type="submit" class="btn sign_up" value="Sign Up">
                <input type="submit" class="btn sign_in" value="Sign In">                
                </form>
            </div>
        </nav>

        <header class="header_container">
            <div class="header_image">
                <div class="header_image_card header_image_card_1">
                    <span><i class="bi bi-chat-square-heart-fill"></i></span>
                    Connection
                </div>

                <div class="header_image_card header_image_card_2">
                    <span><i class="bi bi-clipboard-check-fill"></i></span>
                    Organization
                </div>

                <div class="header_image_card header_image_card_3">
                    <span><i class="bi bi-emoji-heart-eyes-fill"></i></span>
                    Leadership
                </div>

                <div class="header_image_card header_image_card_4">
                    <span><i class="bi bi-person-walking"></i></span>
                    Engagement
                </div>

                <img src="img/illustration.png" alt="header"/>
            </div>

            <div class="header_content">
                <h1>Welcome to<br> <span>Club Manager</span><br> Manage Your Club<br> Efficiently!</h1>

                <p>Club Management System helps streamline operations, connect members, and enhance engagement. 
                    Easily manage events, track participation, and generate insightful reports. 
                    Whether you're a club leader or a member, our platform ensures a seamless experience in organizing and growing your community.!</p>

                <form action="view/login.jsp">
                    <div class="input_row">
                        <div class="input_group">
                            <h5>Location</h5>
                            <div>
                                <span><i class="bi bi-geo-alt-fill"></i></span>
                                <input type="text" placeholder="FPT UNIVERSITY">
                            </div>
                        </div>

                        <div class="input_group">
                            <h5>Date</h5>
                            <div>
                                <span><i class="bi bi-calendar3"></i></span>
                                <input type="text" placeholder="March 21, 2025">
                            </div>
                        </div>
                    </div>
                    <button type="submit">JOIN NOW</button>
                </form>
            </div>
        </header>
        
        <script src="https://unpkg.com/scrollreveal"></script>
        <script src="assets/js/main.js"></script>
    </body>
</html>
