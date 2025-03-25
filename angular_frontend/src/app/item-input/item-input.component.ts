import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { ItemData } from './itemdata-types';

@Component({
  selector: 'app-item-input',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './item-input.component.html',
  styleUrl: './item-input.component.css'
})
export class ItemInputComponent implements OnInit {

  httpClient = inject(HttpClient);

  itemData: ItemData[] = [];
  filteredItemData: any = [];

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData() {
    this.httpClient.get('/api/items').subscribe((data: any) => {
      console.log(data);
      this.itemData = data;
    });
  }

  onFilterChange($event: Event) {
    const newValue = ($event.target as HTMLInputElement).value;
    if (!newValue || newValue.length < 3) {
      return;
    }
    this.filteredItemData = this.itemData.filter(item =>
      JSON.stringify(item).toLowerCase().includes(newValue.toLowerCase())
    );
  }
}
