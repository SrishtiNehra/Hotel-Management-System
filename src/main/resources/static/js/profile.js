console.log("profile.js loaded");

function authHeader() {
    return {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token")
    };
}

// LOAD PROFILE
window.onload = function () {
    loadProfile();
};

function loadProfile() {

    fetch("/api/admins/profile", {
        method: "GET",
        headers: authHeader()
    })
    .then(res => res.json())
    .then(data => {

        document.getElementById("adminId").value = data.adminId;
        document.getElementById("username").value = data.username;
        document.getElementById("password").value = data.password;
        document.getElementById("role").value = data.role;
        document.getElementById("status").value = data.status;

        document.getElementById("usernameView").innerText = data.username;
        document.getElementById("fullNameView").innerText = data.fullName;
        document.getElementById("emailView").innerText = data.email;

    });
}

// OPEN MODAL
function openEdit() {

    document.getElementById("editModal").style.display = "block";

    document.getElementById("fullName").value =
        document.getElementById("fullNameView").innerText;

    document.getElementById("email").value =
        document.getElementById("emailView").innerText;
}

// CLOSE
function closeEdit() {
    document.getElementById("editModal").style.display = "none";
}

// UPDATE (FULL DTO REQUIRED)
function updateProfile() {

    const admin = {
        adminId: document.getElementById("adminId").value,
        username: document.getElementById("username").value,
        password: document.getElementById("password").value,
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        role: document.getElementById("role").value,
        status: document.getElementById("status").value
    };

    fetch("/api/admins/" + admin.adminId, {
        method: "PUT",
        headers: authHeader(),
        body: JSON.stringify(admin)
    })
    .then(res => {
        if (!res.ok) throw new Error();
        return res.json();
    })
    .then(() => {
        alert("Profile updated successfully");
        closeEdit();
        loadProfile();
    })
    .catch(() => {
        alert("Error updating profile");
    });
}