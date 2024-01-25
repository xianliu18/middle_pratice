#include <ngx_config.h>
#include <ngx_core.h>
#include <ngx_http.h>

typedef struct
{
    ngx_http_status_t   status;
    ngx_str_t           backendServer;
} ngx_http_mytest_ctx_t;

typedef struct
{
    ngx_http_stream_conf_t  upstream;
} ngx_http_mytest_conf_t;

static char *
ngx_http_mytest(ngx_conf_t *cf, ngx_command_t *cmd, void *conf);

static ngx_int_t ngx_http_mytest_handler(ngx_http_request_t *r);
static void* ngx_http_mytest_create_loc_conf(ngx_conf_t *cf);
static char *ngx_http_mytest_merge_loc_conf(ngx_conf_t *cf, void *parent, void *child);

static ngx_int_t
mytest_upstream_process_header(ngx_http_request_t *r);
static ngx_int_t
mytest_process_status_line(ngx_http_request_t *r);


static ngx_str_t ngx_http_proxy_hide_headers[] =
{
    ngx_string("Date"),
    ngx_string("Server"),
    ngx_string("X-Pad"),
    ngx_string("X-Accel-Expires"),
    ngx_string("X-Accel-Redirect"),
    ngx_string("X-Accel-Limit-Rate"),
    ngx_string("X-Accel-Buffering"),
    ngx_string("X-Accel-Charset"),
    ngx_null_string
};

static ngx_command_t ngx_http_mytest_commands[] =
{
    {
      ngx_string("mytest"),
      NGX_HTTP_MAIN_CONF | NGX_HTTP_SRV_CONF | NGX_HTTP_LOC_CONF | NGX_HTTP_LMT_CONF | NGX_CONF_NOARGS,
      ngx_http_mytest,
      NGX_HTTP_LOC_CONF_OFFSET,
      0,
      NULL
    },
    ngx_null_command
};

static ngx_http_module_t ngx_http_mytest_module_ctx =
{
  NULL,
  NULL,

  NULL,
  NULL,

  NULL,
  NULL,

  ngx_http_mytest_create_loc_conf,
  ngx_http_mytest_merge_loc_conf
};

ngx_module_t ngx_http_mytest_module =
{
  NGX_MODULE_V1,
  &ngx_http_mytest_module_ctx,
  ngx_http_mytest_commands,
  NGX_HTTP_MODULE,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NGX_MODULE_V1_PADDING
};

static void* ngx_http_mytest_create_loc_conf(ngx_conf_t *cf)
{
  ngx_http_mytest_conf_t *mycf;

  mycf = (ngx_http_mytest_conf_t *)ngx_pcalloc(cf->pool, sizeof(ngx_http_mytest_conf_t));
  if (mycf == NULL)
  {
    return NULL;
  }

  mycf->upstream.connect_timeout = 60000;
  mycf->upstream.send_timeout = 60000;
  mycf->upstream.read_timeout = 60000;
  mycf->upstream.store_access = 0600;

  mycf->upstream.buffering = 0;
  mycf->upstream.bufs.num = 8;
  mycf->upstream.bufs.size = ngx_pagesize;
  mycf->upstream.buffer_size = ngx_pagesize;
  mycf->upstream.busy_buffers_size = 2 * ngx_pagesize;
  mycf->upstream.temp_file_write_size = 2 * ngx_pagesize;
  mycf->upstream.max_temp_file_size = 1024 * 1024 * 1024;


  mycf->upstream.hide_headers = NGX_CONF_UNSET_PTR;
  mycf->upstream.pass_headers = NGX_CONF_UNSET_PTR;

  return mycf;
}

static char *ngx_http_mytest_merge_loc_conf(ngx_conf_t *cf, void *parent, void *child)
{
    ngx_http_mytest_conf_t *prev = (ngx_http_mytest_conf_t *)parent;
    ngx_http_mytest_conf_t *conf = (ngx_http_mytest_conf_t *)child;

    ngx_hash_init_t hash;
    hash.max_size = 100;
    hash.bucket_size = 1024;
    hash.name = "proxy_headers_hash";
    if (ngx_http_upstream_hide_headers_hash(cf, &conf->upstream,
                                            &prev->upstream, ngx_http_proxy_hide_headers, &hash) != NGX_OK) {
        return NGX_CONF_ERROR;
    }

    return NGX_CONF_OK;
}

static ngx_int_t
mytest_upstream_crete_request(ngx_http_request_t *r)
{

}

static ngx_int_t
mytest_process_status_line(ngx_http_request_t *r)
{

}

static ngx_int_t
mytest_upstream_process_header(ngx_http_request_t *r)
{

}

static void
mytest_upstream_finalize_request(ngx_http_request_t *r, ngx_int_t rc)
{
    ngx_log_error(NGX_LOG_DEBUG, r->connection->log, 0, "mytest_upstream_finalize_request");
}

static char *
ngx_http_mytest(ngx_conf_t *cf, ngx_command_t *cmd, void *conf)
{

}