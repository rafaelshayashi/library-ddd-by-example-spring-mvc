create table if not exists book_instance
(
    id         bigserial primary key,
    uuid       uuid not null,
    book_uuid  uuid not null,
    library_id bigint,
    constraint fk_library foreign key (library_id) references library_branch (id)
)