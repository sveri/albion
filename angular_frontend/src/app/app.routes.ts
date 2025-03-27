import { Routes } from '@angular/router';
import { ItemInputComponent } from './item-input/item-input.component';
import { ItemCraftingComponent } from './item-crafting/item-crafting.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

// export const routes: Routes = [];
export const routes: Routes = [{ path: '', component: ItemInputComponent },
{ path: 'crafting', component: ItemCraftingComponent },
{path: '**', component: PageNotFoundComponent}];
