import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { ShipmentService } from 'src/app/service/shipment.service';

@Component({
  selector: 'app-storage-list',
  templateUrl: './storage-list.component.html',
  styleUrls: [],
})
export class StorageListComponent implements OnInit {
  @ViewChild(MatTable) tablaArticulos!: MatTable<Storage>;
  @ViewChild(MatPaginator, { static: true }) paginador!: MatPaginator;

  dataSource: any;

  datos: Storage[] = [];

  columnas: string[] = ['nombreAlmacenamiento', 'tipoAlmacenamiento', 'eliminar'];

  constructor(public shipmentService: ShipmentService) {}

  ngOnInit(): void {
    this.getStorages();
  }

  private getStorages() {
    this.shipmentService.getAllStorages().subscribe(
      (response) => {
        let result = response;
        if (result) {
          this.datos = result;
          this.dataSource = new MatTableDataSource<Storage>(this.datos);
          this.dataSource.paginator = this.paginador;
        } else {
          console.log('error');
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }

  deleteProduct(idMedicamento: number) {
    if (confirm('¿Realmente quiere eliminar este almacenamiento?')) {
      this.shipmentService.deleteProduct(idMedicamento).subscribe(
        (response) => {
          let result = response;
          this.getStorages();
        },
        (err) => {
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
