let adminId = localStorage.getItem("adminId");

window.onload = function () {

    fetch("/api/admins/profile", {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(async res => {

        if (!res.ok) {
            throw new Error("HTTP error " + res.status);
        }

        return res.json();
    })
    .then(data => {

        document.getElementById("adminId").value = data.adminId;
		
		localStorage.setItem("adminId", data.adminId);

        document.getElementById("usernameView").innerText = data.username;
        document.getElementById("fullNameView").innerText = data.fullName;
        document.getElementById("emailView").innerText = data.email;
    })
    .catch(err => {
        console.error("Profile load failed:", err);
    });
};

// OPEN MODAL
function openEdit() {

    document.getElementById("editModal").style.display = "block";

    document.getElementById("fullName").value =
        document.getElementById("fullNameView").innerText;

    document.getElementById("email").value =
        document.getElementById("emailView").innerText;
}

// CLOSE MODAL
function closeEdit() {
    document.getElementById("editModal").style.display = "none";
}

// UPDATE PROFILE
function updateProfile() {

    const adminId = document.getElementById("adminId").value;

    const updatedAdmin = {
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
    .then(async res => {

        if (!res.ok) {
            const err = await res.text();
            throw new Error(err);
        }

        return res.json();
    })
    .then(() => {
        alert("Admin profile updated successfully!");
        location.reload();
    })
    .catch(err => {
        console.error("Update failed:", err);
        alert("Update failed!");
    });
}