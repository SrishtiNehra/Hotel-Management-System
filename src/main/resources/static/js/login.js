function login() {

    const data = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    };

    fetch("/api/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(res => res.json())
        .then(data => {
            if (data.token) {
                localStorage.setItem("token", data.token);
                localStorage.setItem("role", data.role);

                showToast("Login Successful!");
            }





            if (data.role === "ADMIN") {
                window.location.href = "/dashboard/admin";
            }
            else if (data.role === "CUSTOMER") {
                window.location.href = "/dashboard/customer";
            }
            else {
                alert("Invalid role");
            }
        })
        .catch(err => {
            console.error(err);
            alert("Login Failed");
        });
}