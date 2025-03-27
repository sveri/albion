import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemCraftingComponent } from './item-crafting.component';

describe('ItemCraftingComponent', () => {
  let component: ItemCraftingComponent;
  let fixture: ComponentFixture<ItemCraftingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ItemCraftingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemCraftingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
