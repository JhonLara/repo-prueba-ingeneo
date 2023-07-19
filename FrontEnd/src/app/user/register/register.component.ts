import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styles: [],
})
export class RegisterComponent implements OnInit {
  submitted = false;
  userFile: any;
  public imagePath: any;
  imgURL: any;
  pwdd!: string;
  acceptTerms!: string;
  constructor(
    public userService: UserService,
    public fb: FormBuilder,
    public toastr: ToastrService,
    private router: Router
  ) {}

  ngOnInit() {
    this.infoForm();
  }

  get f() {
    return this.userService.formData.controls;
  }

  infoForm() {
    this.userService.formData = this.fb.group({
      userId: null,
      userName: ['', [Validators.required, Validators.minLength(5)]],
      role: ['', [Validators.required, Validators.minLength(8)]],
      email: ['', [Validators.required, Validators.minLength(8)]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  onReset() {
    this.submitted = false;
    this.userService.formData.reset();
  }
  onSubmit() {
    this.submitted = true;
    const val = this.userService.formData.value;
  }

  addData() {
    const users = this.userService.formData.value;
    this.userService.createData(users).subscribe((data) => {
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Usuario registrado',
        showConfirmButton: false,
        timer: 1500
      })
      this.router.navigate(['/login']);
    });
  }

  updateData() {
    this.userService
      .updatedata(
        this.userService.formData.value.id,
        this.userService.formData.value
      )
      .subscribe((data) => {
        this.toastr.success('Modification Faite avec Success');

        this.router.navigate(['/users']);
      });
  }
}
