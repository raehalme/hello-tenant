An example how to implement multi-tenancy in Spring applications
================================================================

This project demonstrates how multi-tenancy can be implemented
in Spring applications using a custom scope. 

The basic idea is that whenever there is a bean which contains
information specific to a single tenant its scope in Spring is 
set to `@TenantScope` (or `scope="tenant"` in XML). 

An example of a bean utilizing the tenant scope is a JDBC 
`DataSource` which connects to a tenant-specific database or a
`RestTemplate` whose base URL and credentials are specific to
each tenant.

Classes in package `com.example.hellotenant` represent the
example application utilizing the custom scope. The actual
implementation for the custom scope can be found under 
`com.example.hellotenant.tenant`.