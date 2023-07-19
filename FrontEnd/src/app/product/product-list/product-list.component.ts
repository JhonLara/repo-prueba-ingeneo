import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Product } from 'src/app/model/product';
import { ShipmentService } from 'src/app/service/shipment.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: []
})
export class ProductListComponent implements OnInit {

  @ViewChild(MatTable) tablaArticulos!: MatTable<Product>;
  @ViewChild(MatPaginator, { static: true }) paginador!: MatPaginator;

  dataSource: any;

  datos: Product[] = [];

  columnas: string[] = ['nombreProducto', 'tipoProducto', 'valor', 'eliminar'];


  constructor(public shipmentService: ShipmentService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  private getProducts() {
    this.shipmentService.getAllProducts().subscribe(
      response => {
        let result = response;
        if (result) {
          this.datos = result;
          this.dataSource = new MatTableDataSource<Product>(this.datos);
          this.dataSource.paginator = this.paginador;          
        } else {
          console.log('error');
        }
      },
      err => {
        console.log(err);
      }
    );
  }

  deleteProduct(idMedicamento: number) {
    if (confirm('¿Realmente quiere eliminar este producto?')) {
      this.shipmentService.deleteProduct(idMedicamento).subscribe(
        response => {
          let result = response;
          this.getProducts();
        },
        err => {
          console.log(err);
        }
      );
    }
  }

  filtrar(event: Event) {
    const filtro = (event.target as HTMLInputElement).value;
    console.log(filtro);
    
    this.dataSource.filter = filtro.trim().toLowerCase();
    this.dataSource.paginator = this.paginador;
  }

}
