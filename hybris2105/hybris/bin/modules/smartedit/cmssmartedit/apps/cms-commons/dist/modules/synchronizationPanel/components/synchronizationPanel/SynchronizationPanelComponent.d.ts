import { EventEmitter, OnDestroy, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { CrossFrameEventService, IAlertService, ICatalogService, ISharedDataService, IWaitDialogService, LogService, SystemEventService, TimerService, TypedMap } from 'smarteditcommons';
import { ISynchronizationPanelApi, ISyncStatusItem } from '../../types';
export declare class SynchronizationPanelComponent implements OnInit, OnDestroy {
    private waitDialogService;
    private logService;
    private crossFrameEventService;
    private systemEventService;
    private timerService;
    private alertService;
    private translateService;
    private sharedDataService;
    private catalogService;
    itemId: string;
    selectAllLabel: string;
    getSyncStatus: (id: string) => Promise<ISyncStatusItem>;
    performSync: (payload: TypedMap<string>[]) => Promise<any>;
    showFooter: boolean;
    getApi: EventEmitter<ISynchronizationPanelApi>;
    selectedItemsUpdate: EventEmitter<ISyncStatusItem[]>;
    syncStatusReady: EventEmitter<ISyncStatusItem>;
    showItemList: boolean;
    message: {
        type: string;
        description: string;
    };
    isLoading: boolean;
    disableList: boolean;
    private SYNC_POLLING_SPEED_PREFIX;
    private syncQueue;
    private selectedItemsStorage;
    private rootItem;
    private resynchTimer;
    private unsubscribeFastFetch;
    private api;
    constructor(waitDialogService: IWaitDialogService, logService: LogService, crossFrameEventService: CrossFrameEventService, systemEventService: SystemEventService, timerService: TimerService, alertService: IAlertService, translateService: TranslateService, sharedDataService: ISharedDataService, catalogService: ICatalogService);
    ngOnInit(): void;
    ngOnDestroy(): void;
    syncItems(): Promise<void>;
    selectionChange(index?: number): void;
    isSyncButtonDisabled(): boolean;
    fetchSyncStatus(eventData?: ISyncStatusItem): Promise<void>;
    markExternalItems(): void;
    getAllItems(): ISyncStatusItem[];
    private setExternalItemsCatalogVersionName;
    private slowDownPolling;
    private speedUpPolling;
    private setRootItem;
    private getRootItem;
    private isRootItemExist;
    private getDependentItems;
    private getCurrentExperience;
    private getSelectedItemPayloads;
    private getSelectedItems;
    private toogleRootItem;
    private toggleAllDependentItems;
    private toggleWaitModal;
    private saveCurrentlySelectedItemsInStorage;
    private getSelectedItemsFromStorage;
    private restoreSelectionAfterFetchingUpdatedItems;
    private updateSyncQueue;
    private showSyncErrors;
    private atLeastOneSelectedItemExists;
    private noSelectedItems;
}
