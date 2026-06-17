function logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("role");

    alert("Logged out successfully");
    window.location.href = "/";
}

window.onload = function () {
    const token = localStorage.getItem("token");

    if (token) {
        document.getElementById("loginLink").style.display = "none";
        document.getElementById("registerLink").style.display = "none";

        document.getElementById("dashboardLink").style.display = "block";
        document.getElementById("logoutLink").style.display = "block";
    }
};