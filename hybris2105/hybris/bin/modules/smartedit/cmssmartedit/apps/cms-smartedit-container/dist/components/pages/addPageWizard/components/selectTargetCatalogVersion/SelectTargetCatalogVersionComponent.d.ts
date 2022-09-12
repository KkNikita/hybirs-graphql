import { OnInit, EventEmitter, ChangeDetectorRef } from '@angular/core';
import { IPageService } from 'cmscommons';
import { FetchStrategy, ICatalogService, ICatalogVersion, IUriContext, SelectApi } from 'smarteditcommons';
import { CatalogVersionRestService } from '../../../../../dao';
import { PageFacade } from '../../../../../facades';
export declare class SelectTargetCatalogVersionComponent implements OnInit {
    private pageFacade;
    private catalogVersionRestService;
    private catalogService;
    private pageService;
    private cdr;
    uriContext: IUriContext;
    pageTypeCode: string;
    pageLabel: string;
    onTargetCatalogVersionSelected: EventEmitter<any>;
    catalogVersions: ICatalogVersion[];
    selectedCatalogVersion: string;
    catalogVersionContainsPageWithSameLabel: boolean;
    catalogVersionSelectorFetchStrategy: FetchStrategy;
    onSelectionChange: () => Promise<void>;
    selectApi: SelectApi;
    constructor(pageFacade: PageFacade, catalogVersionRestService: CatalogVersionRestService, catalogService: ICatalogService, pageService: IPageService, cdr: ChangeDetectorRef);
    ngOnInit(): Promise<void>;
    setSelectApi(api: SelectApi): void;
    private setupCatalogVersions;
    private selectionChangeHandler;
    private determineContentPageWithLabelExists;
    private updateSelectValidationState;
}
