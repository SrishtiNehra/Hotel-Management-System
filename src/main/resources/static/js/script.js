window.onload = function () {

    const role = localStorage.getItem("role");

    if (role) {
        document.getElementById("logoutBtn").style.display = "block";
        document.getElementById("dashboardLink").style.display = "block";
    }
};

function logout() {
    localStorage.clear();
    window.location.href = "/auth/login";
}