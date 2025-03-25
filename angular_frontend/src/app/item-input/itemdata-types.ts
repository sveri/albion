export interface LocalizedText {
    [key: string]: string; // e.g., "EN-US", "DE-DE"
}

export interface ItemData {
    index: string;
    uniqueName: string;
    localizedNames: LocalizedText;
    localizedDescriptions: LocalizedText;
}