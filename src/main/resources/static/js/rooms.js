console.log("rooms.js loaded");

window.onload = function () {
    loadRooms();
};

// ================= TOKEN =================
function authHeader() {
    return {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token")
    };
}

// ================= LOAD ROOMS =================
function loadRooms() {

    fetch("/api/rooms", {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(data => {

        let rows = "";

        data.forEach(r => {

            rows += `
                <tr>
                    <td>${r.roomId}</td>
                    <td>${r.roomNumber}</td>
                    <td>${r.roomType}</td>
                    <td>${r.price}</td>
                    <td>${r.status}</td>
                    <td>
                        <button onclick="openRoomEdit(
                            ${r.roomId},
                            '${r.roomNumber}',
                            '${r.roomType}',
                            '${r.price}',
                            '${r.status}'
                        )">Edit</button>

                        <button onclick="deleteRoom(${r.roomId})">Delete</button>
                    </td>
                </tr>
            `;
        });

        document.getElementById("roomTable").innerHTML = rows;
    });
}

// ================= ADD ROOM =================
function addRoom() {

    const room = {
        roomNumber: document.getElementById("roomNumber").value,
        roomType: document.getElementById("roomType").value,
        price: document.getElementById("price").value,
        status: document.getElementById("status").value,
        hotelId: document.getElementById("hotelId").value
    };

    fetch("/api/rooms", {
        method: "POST",
        headers: authHeader(),
        body: JSON.stringify(room)
    })
    .then(() => {
        alert("Room added!");
        loadRooms();
    });
}

// ================= OPEN EDIT =================
function openRoomEdit(id, number, type, price, status) {

    document.getElementById("editRoomModal").style.display = "block";

    document.getElementById("editRoomId").value = id;
    document.getElementById("editRoomNumber").value = number;
    document.getElementById("editRoomType").value = type;
    document.getElementById("editPrice").value = price;
    document.getElementById("editStatus").value = status;
}

// ================= CLOSE =================
function closeRoomModal() {
    document.getElementById("editRoomModal").style.display = "none";
}

// ================= UPDATE =================
function updateRoom() {

    const id = document.getElementById("editRoomId").value;

    const room = {
        roomNumber: document.getElementById("editRoomNumber").value,
        roomType: document.getElementById("editRoomType").value,
        price: document.getElementById("editPrice").value,
        status: document.getElementById("editStatus").value
    };

    fetch(`/api/rooms/${id}`, {
        method: "PUT",
        headers: authHeader(),
        body: JSON.stringify(room)
    })
    .then(() => {
        alert("Room updated!");
        closeRoomModal();
        loadRooms();
    });
}

// ================= DELETE =================
function deleteRoom(id) {

    if (!confirm("Are you sure?")) return;

    fetch(`/api/rooms/${id}`, {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(() => {
        alert("Room deleted!");
        loadRooms();
    });
}