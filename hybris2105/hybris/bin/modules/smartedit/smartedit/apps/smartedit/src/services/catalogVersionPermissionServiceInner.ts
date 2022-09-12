/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
import {
    GatewayProxied,
    ICatalogVersionPermissionService,
    SeDowngradeService
} from 'smarteditcommons';

@SeDowngradeService(ICatalogVersionPermissionService)
@GatewayProxied()
export class CatalogVersionPermissionService extends ICatalogVersionPermissionService {
    constructor() {
        super();
    }
}
