function register() {

    const role = document.getElementById("role").value;

    const data = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value,
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        role: role,
        status: "ACTIVE"
    };

    let url = role === "ADMIN"
        ? "/api/auth/register/admin"
        : "/api/auth/register/customer";

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(res => res.text())
    .then(msg => {

        showToast(msg);

        // redirect to login after success
        window.location.href = "/auth/login";
    })
    .catch(err => {
        console.error(err);
        alert("Registration Failed");
    });
}