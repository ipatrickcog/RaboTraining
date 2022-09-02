import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../user';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  errormessage: String = "";
  public barLabel: string = "Password strength:";

  addUser = new FormGroup({
    email: new FormControl('', [
      Validators.required
    ]),
    username: new FormControl('', [
      Validators.required
    ]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(5)
    ]),
    confirmpassword: new FormControl('', [
      Validators.required,
      Validators.minLength(5)
    ]),
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

  constructor(private http: HttpClient, private router: Router) {

  }

  get email() { return this.addUser.get('email'); }
  get username() { return this.addUser.get('username'); }
  get password() { return this.addUser.get('password'); }
  get fullname() { return this.addUser.get('fullname'); }
  get gender() { return this.addUser.get('gender'); }
  get dob() { return this.addUser.get('dob'); }
  get address() { return this.addUser.get('address'); }

  onSubmit(): void {
    if (this.addUser.valid) {
      var utemp = this.addUser.getRawValue();
      this.http.post<User>('http://localhost:8080/details/addUser', JSON.stringify(utemp), { observe: 'response' }).subscribe(data => {
        if (data.status == 200) {
          console.warn("User Added!");
          this.router.navigate(['/search']);
          this.errormessage = "";
        }
      }, err => {
        this.errormessage = "User Not Added :("
      })
    }
  }

  ngOnInit(): void {
  }
}
