import { DEMO_JEFE_USUARIO_ID } from './constants';

export class ApiError extends Error {
  constructor(
    message: string,
    readonly status: number
  ) {
    super(message);
    this.name = 'ApiError';
  }
}

function baseUrl(): string {
  const u = import.meta.env.VITE_API_URL as string | undefined;
  return (u && u.replace(/\/$/, '')) || 'http://localhost:8080';
}

export async function apiFetch<T>(path: string, init: RequestInit = {}): Promise<T> {
  const url = `${baseUrl()}${path.startsWith('/') ? path : `/${path}`}`;
  const headers = new Headers(init.headers);
  if (!headers.has('Content-Type') && init.body) {
    headers.set('Content-Type', 'application/json');
  }
  headers.set('X-Usuario-Id', DEMO_JEFE_USUARIO_ID);

  const res = await fetch(url, { ...init, headers });

  if (!res.ok) {
    let msg = res.statusText;
    try {
      const j = (await res.json()) as { message?: string };
      if (j?.message) msg = j.message;
    } catch {
      /* cuerpo no JSON */
    }
    throw new ApiError(msg, res.status);
  }

  if (res.status === 204) {
    return undefined as T;
  }

  const text = await res.text();
  if (!text) {
    return undefined as T;
  }

  return JSON.parse(text) as T;
}
