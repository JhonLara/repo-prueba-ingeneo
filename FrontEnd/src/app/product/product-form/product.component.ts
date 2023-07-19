import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { ShipmentService } from '../../service/shipment.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: [],
})
export class ProductComponent implements OnInit {
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
      productId: null,
      productName: ['', [Validators.required, Validators.minLength(5)]],
      productType: ['', [Validators.required, Validators.minLength(8)]],
      amount: ['', [Validators.required, Validators.minLength(20)]],
    });
  }
  onSubmit() {
    this.submitted = true;
    const val = this.shipmentService.formData.value;
  }

  addData() {
    const formData = new FormData();
    const products = this.shipmentService.formData.value;
    formData.append('product', JSON.stringify(products));
    this.shipmentService.createProduct(formData).subscribe((data) => {
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Producto registrado',
        showConfirmButton: false,
        timer: 1500,
      });
      this.router.navigate(['/login']);
    });
  }
}
