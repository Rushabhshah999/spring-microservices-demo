[ Global Pre Filters ]
↓
[ Route Predicate Match ]
↓
[ Route Filters (GatewayFilterFactory) ]
↓
[ Routing / LoadBalancer ]
↓
[ Route Filters (post-processing) ]
↓
[ Global Post Filters ]

Filter	Layer
AddResponseHeaderGatewayFilterFactory	Route filter
AddGlobalResponseHeadersFilter	        Global filter

So at request time:
GlobalFilter  →  Route Filter

During response handling (your case)  Because :
chain.filter(exchange).then(...)

The execution reverses:
Route Filter (post)  → Global Filter (post)

So:

Route-level AddResponseHeader runs first
Global AddGlobalResponseHeadersFilter runs last

| Filter                                | Called                            |
| ------------------------------------- | --------------------------------- |
| AddResponseHeaderGatewayFilterFactory | **First**                         |
| AddGlobalResponseHeadersFilter        | **Last** (with LOWEST_PRECEDENCE) |
