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
  itemRecipes: any = [];
  filteredItemData: any = [];

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData() {
    this.httpClient.get('/api/items').subscribe((data: any) => {
      this.itemData = data;
    });
    this.httpClient.get('/api/itemrecipes').subscribe((data: any) => {
      this.itemRecipes = data;
    });
  }

  onFilterChange($event: Event) {
    const searchValue = ($event.target as HTMLInputElement).value.trim();

    const searchTerms = searchValue.split(/\s+/);
    if (searchTerms.length > 1) {
      if (searchTerms.some(term => term.length < 2)) {
        return;
      }
      this.filteredItemData = this.filterMultipleTerms(searchTerms, this.itemData);
    } else {
      if (!searchValue || searchValue.length < 3) {
        return;
      }
      this.filteredItemData = this.filterSingleTerm(searchValue, this.itemData);
    }
  }

  filterMultipleTerms(searchTerms: string[], items: ItemData[]) {
    return items.filter(item =>
      searchTerms.every(term =>
        JSON.stringify(item).toLowerCase().includes(term.toLowerCase())
      ));
  }

  filterSingleTerm(searchValue: string, items: ItemData[]) {
    return items.filter(item =>
      JSON.stringify(item).toLowerCase().includes(searchValue.toLowerCase())
    );
  }
}
