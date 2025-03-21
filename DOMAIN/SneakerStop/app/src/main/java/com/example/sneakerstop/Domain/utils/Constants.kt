package com.example.sneakerstop.Domain.utils
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object Constants{
    val supabase = createSupabaseClient(
        supabaseUrl = "https://ssqxobptjyszfogfagyx.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNzcXhvYnB0anlzemZvZ2ZhZ3l4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDIwNjIyNzYsImV4cCI6MjA1NzYzODI3Nn0.PY5pI7lupxcFhb1ddtGk-h2pegw0zHzKsW5KtZj3XRU"
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }
}