async function getPrincipal() {
    let principal = await fetch('http://localhost:8080/admin/authentic').then(res=>res.json());

        let allRoles = '';
        principal.roles.forEach(x=>allRoles += x.role.substring(5) + ' ');
        document.querySelector('#auth_roles').innerHTML = allRoles;
        document.querySelector('#auth_name').innerHTML = principal.name;

    const data = document.querySelector('#person_info');
    data.innerHTML = `<tr>
                          <td>${principal.id}</td>
                          <td>${principal.name}</td>
                          <td>${principal.surname}</td>
                          <td>${principal.email}</td>
                          <td>${principal.age}</td>
                           <td>${allRoles}</td>
                     </tr>
                     `;
}
getPrincipal()


