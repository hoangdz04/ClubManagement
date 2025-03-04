/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const menuBtn = document.getElementById("menu-btn");
const navLinks = document.getElementById("nav-links");
const menuBtnIcon = menuBtn.querySelector("i");

menuBtn.addEventListener("click", (e) => {
    navLinks.classList.toggle("open");
    const isOpen = navLinks.classList.contains("open");
    menuBtnIcon.setAttribute("class", isOpen?"bi bi-x":"bi bi-list");
});

navLinks.addEventListener("click", (e) => {
    navLinks.classList.remove("open");
    menuBtnIcon.setAttribute("class", "bi bi-list");
});

 document.addEventListener("DOMContentLoaded", function () {
        const signInBtn = document.querySelector(".sign_in");
        const signUpBtn = document.querySelector(".sign_up");

        function smoothRedirect(event, url) {
            event.preventDefault();
            document.body.classList.add("fade-out");
            setTimeout(() => {
                window.location.href = url;
            }, 300);
        }

        signInBtn.addEventListener("click", (event) => smoothRedirect(event, "view/login.jsp"));
        signUpBtn.addEventListener("click", (event) => smoothRedirect(event, "view/login.jsp"));
    });

const scrollRevealOption = {
    distance: "50px",
    origin: "bottom",
    duration: 1000
};

ScrollReveal().reveal(".header_image img",{
            ...scrollRevealOption,
            origin: "right",
            delay: 500
});

ScrollReveal().reveal(".header_content h1",{
            ...scrollRevealOption,
            delay: 1000
});

ScrollReveal().reveal(".header_content p",{
            ...scrollRevealOption,
            delay: 1500
});

ScrollReveal().reveal(".header_content form",{
            ...scrollRevealOption,
            delay: 2000
});

ScrollReveal().reveal(".header_image_card",{
    duration: 1000,
    interval: 500,
    delay: 2500
});







