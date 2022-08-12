import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { User } from '../user';

@Component({
  selector: 'app-search-user',
  templateUrl: './search-user.component.html',
  styleUrls: ['./search-user.component.css']
})
export class SearchUserComponent  {

  title = 'User Details Frontend';

  private _req = "http://localhost:8080/details/";

  u: User = {
    id: 0,
    username: "",
    fullname: "",
    age: 0,
    dob: "",
    address: "",
    email: ""
  };

  items = [];
  errormessage: String = "";

  constructor(private http: HttpClient) {
  }

  getUser(userId: string) {
    this.http.get<User>(this._req + userId).subscribe(data => {
      if (data != null && data !== undefined) {
        console.log(data);
        this.u.username = data["username"];
        this.u.fullname = data["fullname"];
        this.u.age = data["age"];
        this.u.dob = data["dob"];
        this.u.address = data["address"];
        this.u.email = data["email"];
        this.errormessage = "";
      } else {
        this.errormessage = "Id is invalid!"
      }
    });
  }

}
