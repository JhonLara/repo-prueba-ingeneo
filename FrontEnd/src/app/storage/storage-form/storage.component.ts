import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { ShipmentService } from '../../service/shipment.service';

@Component({
  selector: 'app-storage',
  templateUrl: './storage.component.html',
  styleUrls: [],
})
export class StorageComponent implements OnInit {
  submitted = false;

  constructor(
    public shipmentService: ShipmentService,
    public fb: FormBuilder,
    public toastr: ToastrService,
    private router: Router
  ) {}

  onReset() {
    this.submitted = false;
    this.shipmentService.formData.reset();
  }
  ngOnInit() {
    this.infoForm();
  }
  infoForm() {
    this.shipmentService.formData = this.fb.group({
      storageId: null,
      storageName: ['', [Validators.required, Validators.minLength(5)]],
      storageType: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
  onSubmit() {
    this.submitted = true;
    const val = this.shipmentService.formData.value;
  }

  addData() {
    const formData = new FormData();
    const storages = this.shipmentService.formData.value;
    formData.append('storage', JSON.stringify(storages));
    this.shipmentService.createStorage(formData).subscribe((data) => {
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Alamcenamiento registrado',
        showConfirmButton: false,
        timer: 1500,
      });
      this.router.navigate(['/storage-list']);
    });
  }
}
