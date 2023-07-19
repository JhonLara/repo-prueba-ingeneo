import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { UserService } from '../../service/user.service';
import {
  FormBuilder,
  FormGroup,
  FormControl,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../model/user';
import { DatePipe } from '@angular/common';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [],
})
export class LoginComponent implements OnInit {
  user: any = {};

  errorMessage!: string;
  name!: string;
  Wdate: any;
  annee!: 0;
  loginForm!: FormGroup;

  constructor(
    private router: Router,
    private userService: UserService,
    public toastr: ToastrService,
    private datePipe: DatePipe,
    public fb: FormBuilder
  ) {}
  ngOnInit() {
    this.loginForm = this.fb.group({
      userName: [null, Validators.required],
      password: [null, Validators.required],
    });
  }

  login() {
    const val = this.loginForm.value;
    this.userService.login(val.userName, val.password).subscribe(
      (res) => {
        this.user = res;
        if (this.user.password == val.password) {
          localStorage.setItem('name', this.user.nom);
          localStorage.setItem('codef', this.user.code);
          localStorage.setItem('role', this.user.role);
          if (this.user.role == 'ADMIN') {
            this.router.navigate(['/accueil1']);
          } else if ((this.user.role = 'FOUR')) {
            this.router.navigate(['/']);
          }
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Sesión iniciada',
            showConfirmButton: false,
            timer: 1500
          })
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Inicio de sesión',
            text: '¡Contraseña incorrecta!',
          });
        }
      },
      (error) => this.toastr.warning('Login Incorrecte ')
    );
  }

  register() {
    this.router.navigate(['/register']);
  }

  logOut() {
    localStorage.removeItem('userName');
  }

  transformDate(date: any) {
    return this.datePipe.transform(date, 'yyyy-MM-dd');
  }
  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('name');
  }
}
