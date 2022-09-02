import { HttpClient, HttpUrlEncodingCodec } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../user';

@Component({
  selector: 'app-search-user',
  templateUrl: './search-user.component.html',
  styleUrls: ['./search-user.component.css']
})
export class SearchUserComponent {

  title = 'User Details Frontend';
  codec = new HttpUrlEncodingCodec;
  deletionPromptMessage = ""

  private _req = "http://localhost:8080/details/";
  private _req2 = "http://localhost:8080/details/allUsers"

  constructor(private http: HttpClient, private modalService: NgbModal) {
    this.getAllUsers();
  }

  // user details to display 
  u: User = {
    id: 0,
    username: "",
    fullname: "",
    gender: "",
    dob: "",
    address: "",
    email: ""
  };

  // nullifier
  u2: User = {
    id: 0,
    username: "",
    fullname: "",
    gender: "",
    dob: "",
    address: "",
    email: ""
  };

  // user to edit
  u3: User = {
    id: 0,
    username: "",
    fullname: "Test",
    gender: "",
    dob: "",
    address: "",
    email: ""
  };

  users: User[] = [];

  items = [];
  errormessage: String = "";
  utd: number = 0;

  editUserForm = new FormGroup({
    id: new FormControl(''),
    email: new FormControl('', [
      Validators.required
    ]),
    username: new FormControl('', [
      Validators.required
    ]),
    // password: new FormControl('', [
    //   Validators.required,
    //   Validators.minLength(5)
    // ]),
    fullname: new FormControl('', [
      Validators.required
    ]),
    gender: new FormControl('', [
      Validators.required
    ]),
    dob: new FormControl('', [
      Validators.required
    ]),
    address: new FormControl('', [
      Validators.required
    ]),
  });

  get email() { return this.editUserForm.get('email'); }
  get username() { return this.editUserForm.get('username'); }
  get password() { return this.editUserForm.get('password'); }
  get fullname() { return this.editUserForm.get('fullname'); }
  get gender() { return this.editUserForm.get('gender'); }
  get dob() { return this.editUserForm.get('dob'); }
  get address() { return this.editUserForm.get('address'); }

  getAllUsers() {
    this.http.get<User[]>(this._req2).subscribe(data => {
      if (data != null && data !== undefined) {
        this.users = data;
      }
    }, err => {
      console.log(err.message);
    });
  }

  getUser(userId: string) {
    this.http.get<User>(this._req + userId).subscribe(data => {
      console.log(data);
      if (data != null && data !== undefined) {
        this.u.username = data["username"];
        this.u.fullname = data["fullname"];
        this.u.gender = data["gender"];
        this.u.dob = data["dob"];
        this.u.address = data["address"];
        this.u.email = data["email"];
        this.errormessage = "";
      } else {
        this.errormessage = "Id is invalid!"
        this.u = this.u2;
      }
    }, err => {
      this.errormessage = "Request Error :("
    });
  }

  editUser() {
    var utemp = this.editUserForm.getRawValue();
    console.log(utemp);
    this.http.put<User>('http://localhost:8080/details/editUser', JSON.stringify(utemp), { observe: 'response' }).subscribe(data => {
      console.log(data);
      window.location.reload();
    }, err => {
      this.errormessage = "User Not Modified :(";
      console.log(err);
    });
  }

  promptDelete(content: any) {
    this.modalService.open(content, { size: 'sm' });
  }

  deleteUser() {
    console.log(this.utd);
    this.http.delete('http://localhost:8080/details/deleteUser/' + this.utd)
      .subscribe(() => {
        this.deletionPromptMessage = "User deleted successfully!";
        window.location.reload();
      });
  }

  openEditor(content: any, userId: number) {
    this.u3 = this.users[userId];
    this.utd = userId + 1;
    // setting default value
    this.editUserForm.patchValue({
      id: userId + 1,
      email: this.u3.email,
      username: this.u3.username,
      fullname: this.u3.fullname,
      gender: this.u3.gender,
      dob: this.u3.dob,
      address: this.u3.address,
    });
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
  }

}
