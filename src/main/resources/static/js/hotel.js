console.log("hotel.js loaded");

window.onload = function () {
    loadHotels();
};

// =======================
// COMMON AUTH HEADER
// =======================
function authHeader() {
    return {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token")
    };
}

// =======================
// LOAD HOTELS
// =======================
function loadHotels() {

    fetch("/api/hotels", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => {
        if (!res.ok) throw new Error("GET failed " + res.status);
        return res.json();
    })
    .then(data => {

        let rows = "";

        data.forEach(h => {
            rows += `
                <tr>
                    <td>${h.hotelId}</td>
                    <td>${h.name}</td>
                    <td>${h.address}</td>
                    <td>${h.city}</td>
                    <td>${h.state}</td>
                    <td>${h.zipCode}</td>
                    <td>${h.contactNumber}</td>
                    <td>${h.email}</td>

                    <td>
                        <button onclick="openEdit(
                            ${h.hotelId},
                            '${h.name}',
                            '${h.address}',
                            '${h.city}',
                            '${h.state}',
                            '${h.zipCode}',
                            '${h.contactNumber}',
                            '${h.email}'
                        )">Edit</button>

                        <button onclick="deleteHotel(${h.hotelId})">Delete</button>
                    </td>
                </tr>
            `;
        });

        document.getElementById("hotelTable").innerHTML = rows;
    })
    .catch(err => console.error("LOAD ERROR:", err));
}

// =======================
// ADD HOTEL
// =======================
function addHotel() {

    const hotel = {
        name: document.getElementById("name").value,
        address: document.getElementById("address").value,
        city: document.getElementById("city").value,
        state: document.getElementById("state").value,
        zipCode: document.getElementById("zipCode").value,
        contactNumber: document.getElementById("contactNumber").value,
        email: document.getElementById("email").value
    };

    fetch("/api/hotels", {
        method: "POST",
        headers: authHeader(),
        body: JSON.stringify(hotel)
    })
    .then(res => {
        if (!res.ok) throw new Error("POST failed " + res.status);
        return res.json();
    })
    .then(() => {
        alert("Hotel added!");
        loadHotels();
    })
    .catch(err => console.error("ADD ERROR:", err));
}

// =======================
// OPEN EDIT MODAL
// =======================
function openEdit(id, name, address, city, state, zip, contact, email) {

    document.getElementById("editModal").style.display = "block";

    document.getElementById("editId").value = id;
    document.getElementById("editName").value = name;
    document.getElementById("editAddress").value = address;
    document.getElementById("editCity").value = city;
    document.getElementById("editState").value = state;
    document.getElementById("editZip").value = zip;
    document.getElementById("editContact").value = contact;
    document.getElementById("editEmail").value = email;
}

// =======================
// CLOSE MODAL
// =======================
function closeModal() {
    document.getElementById("editModal").style.display = "none";
}

// =======================
// UPDATE HOTEL
// =======================
function updateHotel() {

    const id = document.getElementById("editId").value;

    const hotel = {
        name: document.getElementById("editName").value,
        address: document.getElementById("editAddress").value,
        city: document.getElementById("editCity").value,
        state: document.getElementById("editState").value,
        zipCode: document.getElementById("editZip").value,
        contactNumber: document.getElementById("editContact").value,
        email: document.getElementById("editEmail").value
    };

    fetch(`/api/hotels/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify(hotel)
    })
    .then(res => {
        if (!res.ok) throw new Error("UPDATE failed " + res.status);
        return res.json();
    })
    .then(() => {
        alert("Updated successfully");
        closeModal();
        loadHotels();
    })
    .catch(err => console.error("UPDATE ERROR:", err));
}

// =======================
// DELETE HOTEL
// =======================
function deleteHotel(id) {

    if (!confirm("Are you sure?")) return;

    fetch(`/api/hotels/${id}`, {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => {
        if (!res.ok) throw new Error("DELETE failed " + res.status);
        loadHotels();
    })
    .catch(err => console.error("DELETE ERROR:", err));
}