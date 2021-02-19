import { hexToRGB } from 'tutorio/src/js/theme-utils'

// https://material.io/design/color/the-color-system.html#tools-for-picking-colors
const colors = {
  gray: {
    300: '#E1F5F3',
    600: '#95C9C5',
    700: '#6DA39F',
    800: '#154D48',
    900: '#285955'
  },
  primary: {
    50: '#e0f2f1',
    100: '#b1dfdb',
    200: '#7ecbc5',
    300: '#4ab6ad',
    400: '#1EA69B', // $primary
    500: '#009689',
    600: '#00897c',
    700: '#00796b',
    800: '#00695c',
    900: '#004d40'
  },
  accent: {
    50: '#fee3e9',
    100: '#fdb9c8',
    200: '#fa8ca3',
    300: '#f75c7f',
    400: '#f23764',
    500: '#ed0b4b', // primary
    600: '#dd024a',
    700: '#c80047',
    800: '#b40045',
    900: '#920041'
  },
  success: {
    50: '#e8f5e9',
    100: '#c8e6c9',
    200: '#a5d6a7',
    300: '#81c784',
    400: '#66BB6A', // $success
    500: '#4caf50',
    600: '#43a047',
    700: '#388e3c',
    800: '#2e7d32',
    900: '#1b5e20',
  },
  black: '#383B3D',
  white: '#FFFFFF',
  transparent: 'transparent'
}

const fonts = { base: 'Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol"' }

const charts = {
  colorScheme: 'light',
  colors: {
    area: hexToRGB(colors.primary[500], 0.24)
  }
}

export const settings = {
  fonts,
  colors,
  charts
}

if (typeof window !== 'undefined') {
  window.settings = settings
}