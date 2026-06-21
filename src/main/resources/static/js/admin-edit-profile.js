let adminId = localStorage.getItem("adminId");

window.onload = function () {

    fetch("/api/admins/" + adminId, {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(data => {

        document.getElementById("adminId").value = data.adminId;
        document.getElementById("username").value = data.username;
        document.getElementById("fullName").value = data.fullName;
        document.getElementById("email").value = data.email;
    });
};

function updateProfile() {

    let updatedAdmin = {
        adminId: document.getElementById("adminId").value,
        username: document.getElementById("username").value,
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value
    };

    fetch("/api/admins/" + adminId, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify(updatedAdmin)
    })
    .then(res => res.json())
    .then(() => {
        alert("Profile updated successfully!");
        window.location.href = "/admin/profile";
    });
}