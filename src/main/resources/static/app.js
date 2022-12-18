const url = "http://localhost:8080/admin/people";

const credentials = btoa("Pavel:apple")

const container = document.querySelector('#tbody');

let result = '';

const show = (users) => {
    users.forEach(user => {
        result += `   <tr>
                        <td>хуй</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.email}</td>  
                        <td>${user.age}</td>  
                        <td>${user.role}</td>
                    </tr>
                 `
        //container.innerText = result;
    })
}

fetch(url)
    .then(res => res.json())
    .then(data => show(data))
    //.then(x => console.log(x))
    .catch(error => console.log(error))

console.log(show);